pipeline {
    agent any

    stages {
        stage("Build") {
            steps {
                sh "mvn -version"
                sh "mvn clean install"
            }
        }
        stage("Tests") {
            steps {
                sh "mvn -Dtest=*test test" 
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
