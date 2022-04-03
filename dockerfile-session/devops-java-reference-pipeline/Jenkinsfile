pipeline{
    agent {
      label 'docker'
    }
    tools {
      maven 'mvn'
    }
    stages{
    	stage('Build'){
    		steps{
                echo "Starting Build stage"
                sh 'mvn clean package'
            }
            post{
                success{
                    echo "Build completed"
                    junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                     archiveArtifacts 'target/*.jar'
                }
            }
    	}


        stage('Sonar Coverage Analysis'){
            steps{
                echo("Starting Sonar Coverage Analysis")
                 withSonarQubeEnv('sonarqube'){
                  sh "mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar"
                }
            }
            post{
                success{
                    echo "Sonar Coverage Analysis completed"
                }
            }
        }



        stage('Docker Image'){
            steps{
                echo "Creating Docker Image"
                sh "docker build -t arunstiwari/userservice:1.0 ."
            }
            post{
                success{
                    echo "Docker Image Created"
                }
            }
        }


         stage('Acceptance Pipeline Trigger'){
             steps{
                 echo "Triggering Acceptance Pipeline"
                 script{
                     build job: 'acceptance-pipeline'
                 }
             }
         }
    }
}