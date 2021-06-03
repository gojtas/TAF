pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
            withMaven(maven: 'mvn') {
                sh 'mvn -version'
                sh "mvn clean install"
                }
            }
        }
        stage("Tests") {
            steps {
            withMaven(maven: 'mvn') {
                sh "mvn -Dgroups=CONTRACT  test"
                }
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
