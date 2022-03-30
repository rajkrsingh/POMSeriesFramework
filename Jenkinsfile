pipeline{
agent any
  stages{
       stage('Build'){
           steps{
              echo "Building"
           }
           
       } 
       stage('Dev Deploy'){
           steps{
              echo "deploy on dev"
           }
           
       }   
       stage('QA Deploy'){
           steps{
              echo "deploy on QA"
           }
           
       }   
      stage('Sanity Test'){
           steps{
              echo "Sanity test execution"
           }
      }   
      stage('Regression Test'){
           steps{
              echo "Regression test execution"
           }      
       }   
      stage('Stage deployment'){
           steps{
              echo "Stage deployment"
           }
      }   
      stage('Stage sanity test'){
            steps{
              echo "Sanity test on stage"
           }
      }   
      stage('Stage UAT test'){
            steps{
              echo "UAT test on stage"
           }
      }   
        stage('Prod Deployment'){
           steps{
              echo "Deployment on prod"
           }
        }
      
  }
}