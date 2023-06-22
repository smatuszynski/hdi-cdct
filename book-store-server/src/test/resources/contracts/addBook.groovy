package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description 'Add a new book to the library'

    request {
        method POST()
        url '/books'
        headers {
            contentType applicationJson()
        }
        body([
                    title: "Java 12",
                    genre: "Technology",
                    isbn: 4711
        ])
    }
    response {
        status OK()
        body([
                    title: 'Java 12',
                    genre: "Technology",
                    isbn: 4711
        ])
        headers {
            contentType applicationJson()
        }
    }
}
