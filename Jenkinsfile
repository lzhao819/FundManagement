def projectName = 'fundmngmt'
def version = "0.0.${currentBuild.number}"
def dockerImageTag = "${projectName}:${version}"

pipeline {
    agent any
    stages {
        stage('Maven Build Jar File'){
            steps{
             echo "Done!!" //mvn package
            }
        }

        stage('Build Docker Image') {
            steps {
//                 sh "docker stop mysql"
//                 sh "docker stop /app"
//                 sh "docker rm mysql"
//                 sh "docker rm /app"
//                 sh "docker build -f Dockerfile-mysql -t fundmngmt/mysql ."
//                 sh "docker build -f Dockerfile-app -t fundmngmt/app ."
                    sh "docker-compose up"
                }
        }

        stage('Deploy Mysql Container To Openshift') {
              steps {
                sh "oc login --username admin --password admin --insecure-skip-tls-verify=true"
//                 sh "oc project mysql || oc new-project mysql"
//                 sh "oc delete all --selector app=fundmngmt/mysql || echo 'Unable to delete all previous openshift mysql resources'"
//                 sh "oc new-app mysql "
//                 sh "oc expose svc/mysql"
              }
            }
//         stage('Deploy App Container To Openshift') {
//                       steps {
//                         sh "oc login --username admin --password admin --insecure-skip-tls-verify=true"
//                         sh "oc project app || oc new-project app"
//                         sh "oc get service mysql || oc new-app mysql"
//                         sh "oc delete all --selector app=fundmngmt/app || echo 'Unable to delete all previous openshift app resources'"
//                         sh "oc new-app fundmngmt/app  -e DB_HOST=mysql"
//                         sh "oc expose svc/app"
//                       }
//                     }
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