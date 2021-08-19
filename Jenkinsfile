def projectName = 'FundManagementPipeline'
def version = "0.0.${currentBuild.number}"
def dockerImageTag = "${projectName}:${version}"

pipeline {
    agent any
    stages {

        stage('Build docker image') {
            steps {
                sh "docker stop mysql"
                sh "docker stop /app"
                sh "docker rm mysql"
                sh "docker rm /app"
                sh "docker-compose up"
                }
        }
        stage('Get Positions List') {
            steps {
                script {
                    def response = httpRequest 'http://devopsapac48.conygre.com:8080/positions'
                    def json = new JsonSlurper().parseText(response.content)

                    echo "Status: ${response.status}"

                    echo "Dogs: ${json.message.keySet()}"
                }
            }
        }
}