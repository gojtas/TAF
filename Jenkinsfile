pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
            withMaven(maven: 'mvn') {
                sh 'mvn -version'
                }
            }
        }
        stage("Smoke Tests") {
                    steps {
                    withMaven(maven: 'mvn') {
                        sh "mvn -Dgroups=SMOKE test"
                        }
                    }
                }
        stage("Contract Tests") {
            steps {
            withMaven(maven: 'mvn') {
                sh "mvn -Dgroups=CONTRACT test"
                }
            }
        }
        stage("Component Tests") {
            steps {
            withMaven(maven: 'mvn') {
                sh "mvn -Dgroups=COMPONENT test"
                }
            }
        }
        stage("Report") {
            steps {
                ws ("target/allure-docker-api-usage/") {
                    script {
                        allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'allure-results']]
                        ])
                    }
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
