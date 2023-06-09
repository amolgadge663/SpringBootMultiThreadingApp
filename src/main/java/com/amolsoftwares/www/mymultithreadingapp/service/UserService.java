package com.amolsoftwares.www.mymultithreadingapp.service;

import com.amolsoftwares.www.mymultithreadingapp.entity.User;
import com.amolsoftwares.www.mymultithreadingapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    Object target;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public CompletableFuture<List<User>> saveUsers(MultipartFile file) throws Exception {
        long startTime = System.currentTimeMillis();
        List<User> users = parseCSVFile(file);
        logger.info("Saving list of user of size: {}", users.size(), " " + Thread.currentThread().getName());
        users = userRepository.saveAll(users);
        long endTime = System.currentTimeMillis();
        logger.info("Total time : {}", (endTime - startTime));

        return CompletableFuture.completedFuture(users);

    }

    public CompletableFuture<List<User>> findAllUsers() {
        logger.info("Get List of Users by {}", Thread.currentThread().getName());
        List<User> users = userRepository.findAll();

        return CompletableFuture.completedFuture(users);
    }

    private List<User> parseCSVFile(final MultipartFile file) throws Exception {
        final List<User> users = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final User user = new User();
                    user.setName(data[0]);
                    user.setEmail(data[1]);
                    user.setGender(data[2]);
                    users.add(user);
                }

                return users;

            }
        } catch (final IOException e) {
            logger.error("Failed to parse the CSV file {}", e);
            throw new Exception("Failed to parse the CSV file {}", e);
        }
    }
}
