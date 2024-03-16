@echo off

echo ### Criando Chaves no AWS Parameter Store do LocalStack...
@REM aws --endpoint http://localhost:4566 --profile default ssm put-parameter --name "/config/app-fastfood-produto_default/helloWorld" --value "Hello World Parameter Store" --type String
@REM aws --endpoint http://localhost:4566 --profile default ssm put-parameter --name "/config/app-fastfood-produto_default/sqsQueueName" --value "sqsHelloWorld" --type String
@REM aws --endpoint http://localhost:4566 --profile default ssm put-parameter --name "/config/app-fastfood-produto_default/snsNotificationName" --value "snsHelloWorld" --type String
@REM aws --endpoint http://localhost:4566 --profile default ssm put-parameter --name "/config/app-fastfood-produto_default/s3Bucket" --value "s3-helloworld" --type String
@REM aws --endpoint http://localhost:4566 --profile default ssm put-parameter --name "/config/app-fastfood-produto_default/dbUrl" --value "jdbc:mysql://localhost:3306/databasedemo?createDatabaseIfNotExist=true" --type String
@REM aws --endpoint http://localhost:4566 --profile default ssm put-parameter --name "/config/app-fastfood-produto_default/dbUser" --value "admin" --type String
@REM aws --endpoint http://localhost:4566 --profile default ssm put-parameter --name "/config/app-fastfood-produto_default/dbPass" --value "admin123456789" --type String

echo ### Criando Segredos no AWS Secret Manager do LocalStack...
@REM aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/spring-boot-localstack_localstack --description "Exemplo de Secrets Manager" --secret-string "{\"valor1\":\"Oi Mundo\",\"valor2\":\"Hello World\",\"valor3\":\"Hola Mundo\"}"
@REM aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/spring-boot-localstack --description "Exemplo de Secrets Manager" --secret-string "{\"valor1\":\"Oi Mundo\",\"valor2\":\"Hello World\",\"valor3\":\"Hola Mundo\"}"
@REM aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/application --description "Exemplo de Secrets Manager" --secret-string "{\"valor1\":\"Oi Mundo\",\"valor2\":\"Hello World\",\"valor3\":\"Hola Mundo\"}"
@REM aws --endpoint http://localhost:4566 --profile localstack secretsmanager create-secret --name /secret/application_localstack --description "Exemplo de Secrets Manager" --secret-string "{\"valor1\":\"Oi Mundo\",\"valor2\":\"Hello World\",\"valor3\":\"Hola Mundo\"}"

echo ### Criando Bucket no S3 do LocalStack...
aws --endpoint http://localhost:4566 --profile default s3 mb s3://s3-helloworld

echo ### Criando Queue(Standard) no SQS do LocalStack...
aws --endpoint http://localhost:4566 --profile default sqs create-queue --queue-name sqsHelloWorld
aws --endpoint http://localhost:4566 --profile default sqs send-message --queue-url http://localhost:4566/000000000000/sqsHelloWorld --message-body "Hello World SQS!!!" --delay-seconds 1
aws --endpoint http://localhost:4566 --profile default sqs receive-message --queue-url http://localhost:4566/000000000000/sqsHelloWorld

echo ### Criando Queue(Standard) no SNS do LocalStack...
aws --endpoint http://localhost:4566 --profile default sns create-topic --name snsHelloWorld
aws --endpoint http://localhost:4566 --profile default sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:snsHelloWorld --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:000000000000:sqsHelloWorld
