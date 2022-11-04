package com.team.project.test;

import com.team.project.test.TestEntity;
import com.team.project.test.TestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestRepositoryTest {

    @Autowired
    TestRepository testRepository;

    @Test
    void test() {
        testRepository.save(new TestEntity("test", 13));
    }

}