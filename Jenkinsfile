#!groovy

node('master') {
    stage('Checkout') {
        checkout scm
    }

    try{
	    stage('Run tests') {
            sh '/Applications/apache-maven-3.6.1/bin/mvn -Dtest=TestRunner clean test -Dwebdriver.type=remote -Dwebdriver.url=http://192.168.99.100:4444/wd/hub -Dwebdriver.cap.browserName=chrome'
	      }
	} finally {
  		archiveArtifacts artifacts: 'target/**/*'
  	}

    stage('reports') {
	    script {
	            allure([
	                    includeProperties: false,
	                    jdk: '',
	                    properties: [],
	                    reportBuildPolicy: 'ALWAYS',
	                    results: [[path: 'target/allure-results']]
	            ])
           }
        }
	}