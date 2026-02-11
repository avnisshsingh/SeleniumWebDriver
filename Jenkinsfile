pipeline 
{
    agent any
    
tools {
    maven 'maven'
}

    stages 
    {
        stage('Build') 
        {
            steps
            {
                 git 'https://github.com/spring-projects/spring-petclinic.git'
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
           
        }
        
        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }
                
        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/avnisshsingh/SeleniumWebDriver.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regression.xml"
                    
                }
            }
        }
                
     
        stage('Publish Allure Reports') {
           steps {
                script {
						 allure([
   						 commandline: 'allure',
   						 includeProperties: false,
    					 reportBuildPolicy: 'ALWAYS',
   						 results: [[path: 'target/allure-results']]
])
                }
            }
        }
        
        
        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: true, 
                                  reportDir: 'reports', 
                                  reportFiles: 'TestExecutionReport - *.html', 
                                  reportName: 'HTML Extent Report', 
                                  reportTitles: ''])
            }
        }
        
        stage("Deploy to Stage"){
            steps{
                echo("deploy to Stage")
            }
        }
        
        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/avnisshsingh/SeleniumWebDriver.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regression.xml"
                    
                }
            }
        }
        
        stage('Publish sanity Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: true, 
                                  reportDir: 'reports', 
                                  reportFiles: 'TestExecutionReport - *.html', 
                                  reportName: 'HTML Sanity Extent Report', 
                                  reportTitles: ''])
            }
        }
        
    }
}