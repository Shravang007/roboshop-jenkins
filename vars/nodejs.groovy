def call () {
    pipeline {
        agent {
            node { label 'workstation'}
        }

        stages {

            stage('Build') {
                steps {
                    sh 'npm install'
                }
            }
            stage('Unit Tests') {
                steps {
                    echo 'Unit Tests'
                    // sh 'npm test'
                }
            }
            stage('Code Analysis') {
                steps {
                    echo 'sonarqube'
                    //sh 'sonar-scanner -Dsonar.host.url=http://172.31.93.52:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=cart -Dsonar.qualitygate.wait=true'

                }
            }
            stage('Security Scans') {
                steps {
                    echo 'Security Scans'
                }
            }
            stage('Publish a Artifact') {
                when {
                    expression {
                        env.TAG_NAME ==~ ".*"
                    }
                }
                steps {
                    echo 'Publish a Artifact'
                    sh 'env'
                }
            }
        }
    }

}