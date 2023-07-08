# Darts.Saft Web Service 


## Docker Guide
- Dockerfile 로 배포이미지 생성.
```dockerfile  
# docker image 생성 (현재 디렉토리의 소스를 가지고 build 및 배포파일(app.jar) 생성
docker build -f .\DockerFile -t saft_web_java .
```

- docker-compose 를 활용하여 docker container 구동
```dockerfile
# 앞서 생성한 이미지를 활용하여 8080 포트에 컨테이너 구동.
docker-compose -f ./docker-compose.yml up -d
```

