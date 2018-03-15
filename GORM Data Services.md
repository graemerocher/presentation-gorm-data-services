footer: © Object Computing Inc., 2018
slidenumbers: false

![original](images/oci-big-center-center.png)


[.hide-footer]

# [FIT] GORM Data Services
> Taking Your GORM to the Next Level
-- by Graeme Rocher


---

![original](images/oci-bg.png)

[.hide-footer]

# Agenda

* Introduction to Data Services
* Why Data Services
* What's possible with Data Services
* Demos!

---

![original](images/oci-bg.png)

[.hide-footer]

# Why Data Services?

* Users often don't correctly define transaction semantics

```java
class BookController {
	// missing @ReadOnly !
	List<Book> list() { Book.list() }
}

```

* Can lead to performance problems


---

![original](images/oci-bg.png)

[.hide-footer]

# Why Data Services?

* Users often don't correctly define joins

```java
class BookController {
	// missing fetch: [authors: "join"]
	List<Book> list() { Book.list() }
}

```

* Can lead to performance problems

---

![original](images/oci-bg.png)

[.hide-footer]

# Why Data Services?

* Efficiency of update operations

```java
class BookService {
	@Transactional
	Book update(Long id, String title) {
		Book b = Book.get(id)
		b.title = title
		b.save() // updates all properties instead of just title
	}
}

```

---

![original](images/oci-bg.png)

[.hide-footer]

# Why Data Services?

* Making Multi-Tenancy development easier

```java
class BookService {
	@Transactional
	Book get(Long id) {
		Book.withTenant("blah") { Book.get(id) }
	}
}
```
- Wrapping every call with `withTenant` gets boring 


---

![original](images/oci-bg.png)

[.hide-footer]

# [fit] DEMO

* Data Service Basics
