def projectName = 'fundmngmt'
def version = "0.0.${currentBuild.number}"
def dockerImageTag = "${projectName}:${version}"

pipeline {
    agent any
    stages {
        stage('Maven Build Jar File'){
            steps{
                //sh"mvn package"
             echo "Done!!"
            }
        }

        stage('Build Docker Image') {
            steps {
//                 sh "docker stop mysql"
//                 sh "docker stop /app"
//                 sh "docker rm mysql"
//                 sh "docker rm /app"
                sh "docker build -f Dockerfile-mysql -t fundmngmt/mysql ."
                sh "docker build -f Dockerfile-app -t fundmngmt/app ."
                }
        }

        stage('Deploy Mysql Container To Openshift') {
              steps {
                sh "oc login --username admin --password admin --insecure-skip-tls-verify=true"
// //                 sh "oc project fund || oc new-project fund"
//                 sh "oc delete all --selector app=fund || echo 'Unable to delete all previous openshift fund resources'"
//                 sh "oc new-app fundmngmt/mysql "
//                 sh "oc expose svc/mysql!"
              }
            }
    }
    post{
        always{
            echo 'FundManagementPipeline!!'
        }
        success{
                    echo 'FundManagementPipeline Successfully Built！'
        }
        failure{
                    echo 'FundManagementPipeline Failed！'
        }
    }
}