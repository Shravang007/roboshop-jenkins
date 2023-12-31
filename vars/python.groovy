def call() {
    pipeline {
        agent {
            node { label 'workstation'}
        }

        stages {

            stage('Unit Tests') {
                steps {
                    echo 'Unit Tests'
                    // sh 'python -m unittest'
                }
            }
            stage('Code Analysis') {
                steps {
                    echo 'sonarqube'
                    // sh 'sonar-scanner -Dsonar.host.url=http://172.31.93.52:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=payment'
                }
            }
            stage('Security Scans') {
                steps {
                    echo 'Security Scans'
                }
            }
            stage('Publish a Artifact') {
                steps {
                    echo 'Publish a Artifact'
                }
            }
        }
    }
}