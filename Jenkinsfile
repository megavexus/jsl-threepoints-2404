pipeline {
    agent any;
    stages {
        stage ('Checkout'){
            steps {
                
                git credentialsId: 'git-threepoints-github', 
                    url: "git@github.com:megavexus/threepoints_devops_webserver.git",
                    branch: "${BRANCH}"
                  
                /*
                checkout([$class: 'GitSCM', 
                    branches: [[name: "refs/heads/${BRANCH}"]], 
                    doGenerateSubmoduleConfigurations: false, 
                    extensions: [[$class: 'CleanBeforeCheckout', deleteUntrackedNestedRepositories: true]], 
                    submoduleCfg: [], 
                    userRemoteConfigs: [
                        [
                            credentialsId: 'git-threepoints-github', 
                            url: 'git@github.com:megavexus/threepoints_devops_webserver.git'
                        ]
                    ]
                ])
                */
                
            }
        }
        stage ('sonar Analysis') {
            environment {
                def scannerHome = tool 'SonarScanner'
            }
            steps {
                withSonarQubeEnv(credentialsId: 'sonar-token-v2', installationName: 'Sonar Local') {
                    sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=threepoints_2404"
                }
            }
        }
        stage ('check QualityGate') {
            steps {
                sleep 5
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: false, credentialsId: 'sonar-token-v2'
                }
                /*
                // Esto vale para scripted o para JSL
                def qualitygate = waitForQualityGate(credentialsId: 'sonar-token-v2')
                if (qualitygate.status != "OK") {
                    error "Pipeline aborted due to quality gate coverage failure: ${qualitygate.status}"
                }
                */
            }
        }
    }
}
