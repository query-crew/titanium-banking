package com.titanium.user.repository;

import java.util.List;

import com.titanium.user.model.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Integer> {
    boolean existsBySocialSecurityNumber(String socialSecurityNumber);

    List<Member> findAll();

    Member findById(int id);

    List<Member> findAllByFirstNameAndLastName(String firstName, String lastName);
}
