pipeline {
    agent any
tools
{
maven 'M2_HOME'
}
 environment {

        registry = "onsbenmhamed/devops"

        registryCredential = 'dockerHub'

        dockerImage = ''

    }
   
    stages {
        stage('Checkout GIT') {
            steps {
                git branch:'Ons',
                url:'https://github.com/onss1/DevOps.git'
               
                script{
                                    Date date = new Date()
String datePart = date.format("dd/MM/yyyy")
String timePart = date.format("HH:mm:ss")

println "date : " + datePart + "\ttime : " + timePart
                }

            }
        }
          stage('MVN CLEAN')
        {
        steps {
        sh """mvn -version"""
        sh 'mvn clean'
        }
       
        }
     
       
       
        stage('MVN PACKAGE')
        {
        steps {
        sh 'mvn package'
        }
        }  
       
        stage('MVN SONARQUBE')
        {
        steps{
        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
        }
        }
   
        stage('MVN DEPLOY')
        {
        steps {
        sh 'mvn deploy'
        }
        }

        stage('MVN TEST')
        {
        steps {
        sh 'mvn test'
        }
        }

          stage('Building our image') {

            steps {

                script {

                    dockerImage = docker.build registry + ":$BUILD_NUMBER"

                }

            }

        }

        stage('Deploy our image') {

            steps {

                script {

                    docker.withRegistry( '', registryCredential ) {

                        dockerImage.push()

                    }

                }

            }

        }

        stage('Cleaning up') {

            steps {

                sh "docker rmi $registry:$BUILD_NUMBER"

            }

        }
       
        stage('Docker compose ')
        {
        steps {
        sh 'docker-compose down '
        sh 'docker-compose up -d'
        }
        }
            stage('MVN TEST')
        {
        steps {
        sh 'mvn test'
        }
        }


       }
       
    }