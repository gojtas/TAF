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
                sh "cd ./src/test/java/com.tests/ComponentTests/; mvn test -Dgroups=COMPONENT"
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
