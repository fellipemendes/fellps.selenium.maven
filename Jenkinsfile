#!groovy

node('master') {
    stage('Checkout') {
        checkout scm
    }

    try{
	    stage('Run tests') {
            sh '/Applications/apache-maven-3.6.1/bin/mvn -Dtest=TestRunner clean test'
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