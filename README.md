# SpringBootMultiThreadingApp
This is a repo which demonstrate Multi-Threading in Spring Boot Project.

Generating a random CSV file you can use --> [Mockaroo - Random Data Generator and API Mocking Tool | JSON / CSV / SQL / Excel](https://www.mockaroo.com/)

There are three endpoints:
1. POST /users --> it required csv file 
2. GET /users --> it will return all the users without multithreading
3. GET /usersByMultithreading --> using multithreading return all the users. (3 threads)
