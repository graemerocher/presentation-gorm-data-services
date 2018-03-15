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
package example.part_01

import example.Neo4jSpec
import example.Product
import grails.gorm.services.Service
import grails.gorm.transactions.Rollback

/**
 * @author graemerocher
 * @since 1.0
 */
class BasicsSpec extends Neo4jSpec {

    @Rollback
    void "test data service basics"() {
        given:
        ProductService productService = datastore.getService(ProductService)

        expect:
        productService.countProducts() == 2
    }
}

@Service(Product)
interface ProductService {
    int countProducts()
}
