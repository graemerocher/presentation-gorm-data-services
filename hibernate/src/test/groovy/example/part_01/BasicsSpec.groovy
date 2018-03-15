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

import example.HibernateSpec
import example.Product
import grails.gorm.services.Query
import grails.gorm.services.Service
import grails.gorm.transactions.Rollback
import rx.Observable

/**
 * @author graemerocher
 * @since 1.0
 */
class BasicsSpec extends HibernateSpec {

    void "test data service basics"() {
        given:
        ProductService productService = datastore.getService(ProductService)

        expect:
        productService.findProductType("Apple") == "Fruit"
    }
}

@Service(Product)
interface ProductService {
    int countProducts()


    Observable<Product> find(String name)

    @Query("update ${Product p} set ${p.type} = $type where ${p.id} = $id")
    void update(Long id, String type)


    @Query("select new map(${p.type} as type, ${p.name} as name) from ${Product p} where ${p.type} = $type")
    List<Map<String, String>> findByType(String type)


    String findProductType(String name)

}
