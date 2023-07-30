//def call() {
//    node('workstation') {
//
//        if (env.cibuild == "java") {
//
//            stage('Build') {
//                echo 'mvn package'
//                    //sh 'mvn package'
//        }
//    }
//            stage('Unit tests') {
//                    echo 'unit tests'
//                    // sh 'mvn test'
//            }
//
//            stage('Code Analysis') {
//                    echo 'sonar'
//                    // sh 'sonar-scanner -Dsonar.host.url=http://172.31.93.52:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=frontend
//            }
//
//            stage('Security Scans') {
//                    echo 'Security Scans'
//            }
//
//         if (env.TAG_NAME ==~ ".*") {
//             stage('Publish a Artifact') {
//
//                 echo 'Publish a Artifact'
//
//         }
//
//            }
//
//
//        }
//
//    }
//



def call() {
    node('workstation') {

        stage('Code Checkout') {

            sh 'find . | grep "^./" |xargs rm -rf'

            if(env.TAG_NAME ==~ ".*") {
                env.gitbrname = "refs/tags/${env.TAG_NAME}"
            } else {
                env.gitbrname = "${env.BRANCH_NAME}"
            }
            checkout scm: [$class: 'GitSCM', userRemoteConfigs: [[url: "https://github.com/Shravang007/${env.component}"]], branches: [[name: gitbrname]]], poll: false

        }

        if (env.cibuild == "java") {

            stage('Build') {
                sh 'mvn package'
            }
        }

        if (env.cibuild == "nodejs") {

            stage('Build') {
                sh 'npm install'
            }
        }

        stage('Unit tests') {
            echo 'unit tests'
            sh 'ls -ltr'
        }

        stage('Code Analysis') {
            echo 'sonar'
            //sh 'sonar-scanner -Dsonar.host.url=http://172.31.93.52:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=frontend -Dsonar.qualitygate.wait=true'
        }

        stage('Security Scans') {
            echo 'Security Scans'
        }

        if(env.TAG_NAME ==~ ".*") {
            stage('Publish an Artifact') {
                if (env.cibuild == "java") {
                    sh 'mv target/${component}-1.0.jar ${component}.jar'
                    sh 'rm -rf pom.xml src target'
                }
                sh 'rm -f Jenkinsfile'
                sh 'echo ${TAG_NAME} >VERSION'
                sh 'zip -r ${component}-${TAG_NAME}.zip *'
                sh 'curl -v -u admin:admin123 --upload-file ${component}-${TAG_NAME}.zip http://172.31.88.112:8081/repository/${component}/${component}-${TAG_NAME}.zip'
            }
        }

    }
}