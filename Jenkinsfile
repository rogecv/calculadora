pipeline {
    agent any
    
    tools {
        //Que herramientas queremos utilizar 
        maven "Maven"
        jdk "Java11"
    }

    environment {
        DISABLE_AUTH = 'true'
        DB_ENGINE    = 'sqlite'
    }
    
    stages {
        stage('Hello') {
            steps {
                echo "Database engine is ${DB_ENGINE}"
                echo "DISABLE_AUTH is ${DISABLE_AUTH}"
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
                echo "Job Name: ${env.JOB_NAME}"
                echo "Job Name: ${env.JAVA_HOME}"
            }
        }
        
        stage('Git Polling'){
            steps{
                git branch:'main', credentialsId:'GitHubKey', url:'git@github.com:rogecv/calculadora.git'   
            }
        }
        
        stage('BUILD CON MAVEN'){
            steps{
                //sh "mvn clean install -U -DskipTests -f pom.xml"
               sh "mvn -Dmaven.test.failure.ignore=true clean package " //Unix
            }
            post{
                success{
                    echo 'Archivar Artefactos'
                    archiveArtifacts "target/*.jar"
                }
            }
        }
        stage('test maven'){
            steps{
                sh "mvn test -X"
            }
        }
		stage('Coverage Report') {
			steps {
			// Paso para generar el informe de cobertura
				 bat 'mvn jacoco:prepare-agent test jacoco:report' // Comando para generar el informe de jacoco con Maven
			}
		}
		stage('Notification') {
			steps {
			// Notificar por correo electrónico
                emailext (
                subject: 'Error en el pipeline de Jenkins',
                body: 'Se ha detectado un error en el pipeline de Jenkins. Por favor, revisa el registro de Jenkins para obtener más detalles.',
                to: 'rogelio.cisternas@gmail.com',
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                )      
            }
        }
    }
	post {
       	always {
			// Archivar los informes de cobertura
			jacoco(execPattern: '**/target/jacoco.exec')
           	jacoco(classPattern: '**/target/classes', sourcePattern: 'src/main/java')
        }
    }
	
}