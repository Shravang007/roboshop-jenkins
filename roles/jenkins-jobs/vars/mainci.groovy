def call() {
   node('workstation') {

          if (env.cibuild == "java") {

              stage('Build') {
                  sh 'mvn package'
              }
          }

            stage('Unit Tests') {
                    echo 'Unit Tests'
            }

            stage('Code Analysis') {
                    echo 'sonarqube'
                    // sh 'sonar-scanner -Dsonar.host.url=http://172.31.93.52:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=frontend'
            }

            stage('Security Scans') {
                    //Checkmarx Scan
                    echo 'Security Scans'
            }

            stage('Publish a Artifact') {
                    echo 'Publish a Artifact'
            }

        }
    }
