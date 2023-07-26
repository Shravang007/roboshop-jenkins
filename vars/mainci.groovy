def call() {
    node('workstation') {

        if (env.cibuild == "java") {

            stage('Build') {
                echo 'mvn package'
                    //sh 'mvn package'
        }
    }
            stage('Unit tests') {
                    echo 'unit tests'
                    // sh 'mvn test'
            }

            stage('Code Analysis') {
                    echo 'sonar'
                    // sh 'sonar-scanner -Dsonar.host.url=http://172.31.93.52:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=frontend
            }

            stage('Security Scans') {
                    echo 'Security Scans'
            }

         if (env.TAG_NAME ==~ ".*") {
             stage('Publish a Artifact') {

                 echo 'Publish a Artifact'

         }

            }


        }

    }