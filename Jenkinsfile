def projectName = 'FundManagementPipeline'
def version = "0.0.${currentBuild.number}"
def dockerImageTag = "${projectName}:${version}"

pipeline {
    agent any
    stages {
        stage('Maven Build Jar File'){
            echo "Done!!" //mvn package
        }

        stage('Build Docker Image') {
            steps {
                //remove existing container
                sh "docker stop mysql"
                sh "docker stop /app"
                sh "docker rm mysql"
                sh "docker rm /app"
                sh "docker build -f Dockerfile-mysql -t fundmngmt/mysql ."
                sh "docker build -f Dockerfile-app -t fundmngmt/app ."
                }
        }

        stage('Deploy Container To Openshift') {
              steps {
//                 sh "oc login -u ${OPENSHIFT_CREDS_USR} -p ${OPENSHIFT_CREDS_PSW}"
//                 sh "oc project ${projectName} || oc new-project ${projectName}"
//                 sh "oc get service mongo || oc new-app mongo"
//                 sh "oc delete all --selector app=${projectName} || echo 'Unable to delete all previous openshift resources'"
//                 sh "oc new-app ${dockerImageTag} -l version=${version} -e DB_HOST=mongo"
//                 sh "oc expose svc/${projectName}"

                sh "oc login --username admin --password admin --insecure-skip-tls-verify=true"
                sh "oc project ${projectName} || oc new-project ${projectName}"
                sh "oc delete all --selector app=${projectName} || echo 'Unable to delete all previous openshift resources'"
                sh "oc new-app ${dockerImageTag} -l version=${version}"
                sh "oc expose svc/${projectName}"
              }
            }

//         stage('Get Positions List') {
//             steps {
//                 script {
//                     def response = httpRequest 'http://devopsapac48.conygre.com:8080/positions'
//                     //def json = new JsonSlurper().parseText(response.content)
//
//                     println("Status: "+response.status)
//                     println("Content: "+response.content)
//
// //                     echo "Status: ${response.status}"
// //
// //                     echo "Dogs: ${json.message.keySet()}"
//                 }
//             }
//         }
    }
    post{
        always{
            echo 'FundManagementPipeline'
        }
        success{
                    echo 'FundManagementPipeline Successfully Built'
        }
        failure{
                    echo 'FundManagementPipeline Failed'
        }
    }
}