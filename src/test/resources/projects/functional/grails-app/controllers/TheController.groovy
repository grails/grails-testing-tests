class TheController {

    def grailsApplication
    
    def warDeployed = {
        render(text: grailsApplication.warDeployed.toString())
    }

}