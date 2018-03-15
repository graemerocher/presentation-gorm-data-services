/*
 * Copyright 2018 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example


import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.mongo.tests.MongodForTestsFactory
import grails.gorm.transactions.TransactionService
import org.grails.datastore.mapping.core.DatastoreUtils
import org.grails.datastore.mapping.mongo.MongoDatastore
import org.grails.datastore.mapping.mongo.config.MongoSettings
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class MongoSpec extends Specification{

    @Shared MongodForTestsFactory factory = MongodForTestsFactory.with(Version.Main.PRODUCTION)
    @Shared @AutoCleanup MongoDatastore datastore = new MongoDatastore(
            factory.newMongo(),
            DatastoreUtils.createPropertyResolver(null),
            MongoSpec.getPackage()
    )

    def setup() {
        TransactionService transactionService = datastore.getService(TransactionService)
        transactionService.withNewTransaction {
            new Product(name: "Apple", type:"Fruit")
                    .addToAttributes(name: "Green")
                    .save()
            new Product(name: "Orange", type:"Fruit")
                    .addToAttributes(name: "Juicy")
                    .save()

        }
    }

    def cleanup() {
        datastore.mongoClient.dropDatabase(MongoSettings.DEFAULT_DATABASE_NAME)
    }
    def cleanupSpec() {
        factory.shutdown()
    }

}
