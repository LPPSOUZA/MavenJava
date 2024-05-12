job('Java Maven App DSL 2') {
    description('Java Maven App con DSL para o curso de Jenkins')
     scm {
        git('https://github.com/LPPSOUZA/MavenJava.git', 'master') {node ->
            node / gitConfigName('LPPSOUZA')
            node / gitConfigEmail('leonardo.souza132@fatec.sp.gov.br')
        }
    }
    steps {
        maven {
          mavenInstallation('MavenJenkins')
          goals('-B -DskipTests clean package')
        }
        maven {
          mavenInstallation('MavenJenkins')
          goals('test')
        }
        shell('''
         echo "Entrega: executando a aplicação" 
         java -jar 'C:/ProgramData/Jenkins/.jenkins/workspace/JavaAppComMaven/target/my-app-1.0-SNAPSHOT.jar'
        ''')  
    }
    publishers {
        archiveArtifacts('target/*.jar')
        archiveJunit('target/surefire-reports/*.xml')
	
    }
}
