# embl-personManagement
    This application serves the purpose of managing persons details with store/update/retrieve and delete functionalities .

# Tech Stack :
    Service -
        SpringBoot - 2.2.2.RELEASE .
        Database- H2 (in Memory).
        JWT token authentication implemented .

# The application is running at port 9090 .

# Build:
  * Install dependencies -
    #install maven  :
    sudo apt-get -y install maven

  * Build/Compile & run unit Tests
    Execute the below command inside "embl-personManagement" folder .
    mvn clean install


# Run :
  Execute the below command inside "embl-personManagement" folder .
    java -jar ./target/mySpringBootRestApp-1.0-SNAPSHOT.jar


# HealthCheck
    http://localhost:8080/api/v1/health


#Steps to hit API :

1. Since the API's are JWT authentication protected , we would have to generate a Bearer token in order to successfully get the response .

    Example :
    Request :
    curl -X POST \
      http://localhost:8080/authenticate \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: 46208df4-6ae6-ce22-8aa4-b2164ea9643c' \
      -d '{"username":"happy" , "password":"password"}'

    Response :
        {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYXBweSIsImV4cCI6MTU4MTg4NDI0OCwiaWF0IjoxNTgxODY2MjQ4fQ.86IY_915HiOFuNvcZLp0EkvD0t-fRf_Egp87n-4Qy0o9aZ_6LHgJXS8JIT-SDPtLkeFKgzR0Bz3WgKMWoZRK8Q"
        }

2. The consecutive API's can be called with the above token in the Authorization Header :
    Example :

    curl -X GET \
  http://localhost:8080/api/v1/health \
  -H 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYXZhaW51c2UiLCJleHAiOjE1ODE4ODMzMTcsImlhdCI6MTU4MTg2NTMxN30.n5k9uYk81vquqF1t0ilRHoRwlViMqchDca0Lrk7HSBfX_3ApaTs0WK19C4BNM-q4kZAmC7iAxPxpcK2SGg1XSg' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: b1418543-367b-ee48-6221-098db2d8a626'




