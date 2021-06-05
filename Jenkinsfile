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

            post {
                    script {
                        allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                        ])
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
