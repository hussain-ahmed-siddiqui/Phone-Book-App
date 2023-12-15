package com.cloudassest.intern.phone_book.repositories;

import com.cloudassest.intern.phone_book.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact,String> {
}
