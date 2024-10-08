pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'vijay448/springbootapp' // Your Docker Hub username and image name
        DOCKERHUB_CREDENTIALS = 'dockerhub-credentials' // Jenkins credentials ID for Docker Hub
        MAVEN_HOME = tool name: 'maven', type: 'maven' 
    }

    stages {
        stage('Checkout') {
            steps {
                // Clone the repository from GitHub
                git url: 'https://github.com/vijayjumbad/SpringBootDockerJenkins.git', branch: 'main'
            }
        }
        stage('Build') {
            steps {
                withEnv(["PATH+MAVEN=${MAVEN_HOME}/bin"]) {
                    // Build the Maven project
                    bat 'mvn clean package' // Use bat instead of sh for Windows
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                // Build the Docker image
                script {
                    // Change directory to where the Dockerfile is located
                    dir('.') {
                        docker.build(DOCKER_IMAGE)
                    }
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                // Log in to Docker Hub
                withDockerRegistry([credentialsId: DOCKERHUB_CREDENTIALS, url: 'https://index.docker.io/v1/']) {
                    // Push the Docker image to Docker Hub
                    bat "docker push ${DOCKER_IMAGE}" // Use bat instead of sh for Windows
                }
            }
        }
        stage('Deploy') {
            steps {
                // Run the Docker container using Windows command syntax
                bat "docker run -d -p 8181:8080 ${DOCKER_IMAGE}" // Use bat instead of sh for Windows
            }
        }
    }

    post {
        always {
            // Clean up the workspace
            cleanWs()
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}