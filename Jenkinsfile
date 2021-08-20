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
                sh "docker build -f Dockerfile-mysql -t fundmngmt/mysql ."
                sh "docker build -f Dockerfile-app -t fundmngmt/app ."
                }
        }

        stage('Deploy Mysql Container To Openshift') {
              steps {
                sh "oc login --username admin --password admin --insecure-skip-tls-verify=true"
                sh "oc project fundmngmt/mysql || oc new-project fundmngmt/mysql"
                sh "oc delete all --selector app=fundmngmt/mysql || echo 'Unable to delete all previous openshift mysql resources'"
                sh "oc expose svc/fundmngmt/mysql"
              }
            }
        stage('Deploy App Container To Openshift') {
                      steps {
                        sh "oc login --username admin --password admin --insecure-skip-tls-verify=true"
                        sh "oc project fundmngmt/app || oc new-project fundmngmt/app"
                        sh "oc get service fundmngmt/mysql || oc new-app fundmngmt/mysql"
                        sh "oc delete all --selector app=fundmngmt/mysql || echo 'Unable to delete all previous openshift mysql resources'"
                        sh "oc expose svc/fundmngmt/app"
                      }
                    }
    }
    post{
        always{
            echo 'FundManagementPipeline'
        }
        success{
                    echo 'FundManagementPipeline Successfully Built！'
        }
        failure{
                    echo 'FundManagementPipeline Failed！'
        }
    }
}