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
                        sh "cp -r /target/allure-results $JOB_BASE_NAME/target/allure-results"
                        }
                    }
                }
        stage("Contract Tests") {
            steps {
            withMaven(maven: 'mvn') {
                sh "mvn -Dgroups=CONTRACT test"
                sh "cp -r /target/allure-results $JOB_BASE_NAME/target/allure-results"
                }
            }
        }
        stage("Component Tests") {
            steps {
            withMaven(maven: 'mvn') {
                sh "mvn -Dgroups=COMPONENT test"
                sh "cp -r /target/allure-results $JOB_BASE_NAME/target/allure-results"
                }
            }
        }
        stage("Report") {
            steps {
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

    post {
        always {
            cleanWs()
        }
    }
}
