
class ProjectJira {
    String projectID
    String projectName
    String projectKey
    List projectDictionary = [["Project name": "adVentures", "Project key": "ADV", "Project ID": 16300],
                    ["Project name": "Casinia V3", "Project key": "CSN", "Project ID": 16102],
                    ["Project name": "Core V2 B2B", "Project key": "V2B2B", "Project ID": 15200],
                    ["Project name": "Core V3", "Project key": "V3", "Project ID": 14301],
                    ["Project name": "EVO", "Project key": "EVO", "Project ID": 15800],
                    ["Project name": "Game Service Provider", "Project key": "GSP", "Project ID": 13604],
                    ["Project name": "Great X", "Project key": "GREAT", "Project ID": 16103],
                    ["Project name": "LuckyHeroes", "Project key": "LUHE", "Project ID": 16803],
                    ["Project name": "Mobile Application Development", "Project key": "MAD", "Project ID": 16301],
                    ["Project name": "Mr_Pacho", "Project key": "MRPC", "Project ID": 16900],
                    ["Project name": "My empire_V3", "Project key": "MEM", "Project ID": 16101],
                    ["Project name": "Napoleon", "Project key": "NPLN", "Project ID": 17002],
                    ["Project name": "NRUIKIT", "Project key": "NRUI", "Project ID": 15900],
                    ["Project name": "Payment gateway", "Project key": "PGW", "Project ID": 15600],
                    ["Project name": "Priority Team 1", "Project key": "PRIOR1", "Project ID": 15502],
                    ["Project name": "Pro Team", "Project key": "PRO", "Project ID": 17201],
                    ["Project name": "SG Casino", "Project key": "SGC", "Project ID": 17100],
                    ["Project name": "Sportuna", "Project key": "SP", "Project ID": 16500],
                    ["Project name": "Support Existing Brands B2B", "Project key": "SEBB2B", "Project ID": 16503],
                    ["Project name": "Unified Frontend Core", "Project key": "UFC", "Project ID": 16104],
                    ["Project name": "Unknown Team", "Project key": "UNT", "Project ID": 17203],
                    ["Project name": "VinylCasino", "Project key": "VIC", "Project ID": 17402],
                    ["Project name": "Xplatform", "Project key": "XPLT", "Project ID": 17501],
                    ["Project name": "Core V2", "Project key": "V2", "Project ID": 15105],
    ]

    def String getProjectIdByKey(String key) {
        this.projectDictionary.each { project ->
            if (key == project["Project key"]){
                this.projectID = project["Project ID"]
            }
        }
        return this.projectID
    }

    def String getProjectNameByKey(String key) {
        this.projectDictionary.each { project ->
            if (key == project["Project key"]){
                this.projectName = project["Project name"]
            }
        }
        return this.projectName
    }
}


class IssueTypeJira {
    String issuetypeName
    String issuetypeId
    List issuetypeDictionary = [
        ["Issuetype Name": "Epic", "Issuetype ID": 10101],
        ["Issuetype Name": "Story", "Issuetype ID": 10100],
        ["Issuetype Name": "Bug", "Issuetype ID": 10200],
        ["Issuetype Name": "Initiative", "Issuetype ID": 11101],
    ]

    def String getIssuetypeIdByName(String name) {
        this.issuetypeDictionary.each { issuetype ->
            if (name == issuetype["Issuetype Name"]){
                this.issuetypeId = issuetype["Issuetype ID"]
            }
        }
        return this.issuetypeId
    }

    def String getIssuetypeNameById(String name) {
        this.issuetypeDictionary.each { issuetype ->
            if (name as Long == issuetype["Issuetype ID"]){
                this.issuetypeName = issuetype["Issuetype Name"]
            }
        }
        return this.issuetypeName
    }
}


import com.onresolve.jira.behaviours.restservice.PortalScriptFieldsEndpoint
import com.atlassian.jira.component.ComponentAccessor

class PortalFields {
    String groupOfRequests
    String typeOfRequest
    List fields
    List fieldsConfiguration =[
                        ["Group of Requests": "Payments", "Type of request": "Change Request", "Fields": [
                                                ["portalName": "Summary", "jiraName": "Summary"],
                                                ["portalName": "Priority", "jiraName": "Priority"],
                                                ["portalName": "Visibility", "jiraName": "Visibility"],
                                                ["portalName": "Legal side is ready project approved", "jiraName": "Legal side is ready/ project approved"],
                                                ["portalName": "Brands", "jiraName": "Brands"],
                                                ["portalName": "Action", "jiraName": "Action"],
                                                ["portalName": "Payment method name", "jiraName": "Payment method"],
                                                ["portalName": "Countries", "jiraName": "Countries List"],
                                                ["portalName": "Payment icon", "jiraName": "Payment icon"],
                                                ["portalName": "Type of transaction", "jiraName": "Type of transaction"],
                                                ["portalName": "Payment System", "jiraName": "PSP"],
                                                ["portalName": "Available currencies", "jiraName": "Available currencies"],
                                                ["portalName": "Reason", "jiraName": "Reason"],
                                                ["portalName": "Description", "jiraName": "Description"],
                                                ["portalName": "End Date", "jiraName": "End Date"],
                                                ["portalName": "Labels", "jiraName": "Labels"]
                                                ]
                        ]
                    ]
    
    PortalFields(String groupOfRequests, String typeOfRequest){
        this.groupOfRequests = groupOfRequests
        this.typeOfRequest = typeOfRequest
        getFieldsList(this.groupOfRequests, this.typeOfRequest)
    }

    private List getFieldsList (String groupOfRequests, String typeOfRequest){
        List fieldsList
        this.fieldsConfiguration.each { requestFieldsConfig ->
            if (requestFieldsConfig["Group of Requests"] == groupOfRequests && requestFieldsConfig["Change Request"] == typeOfRequest) {
                fieldsList = requestFieldsConfig["Fields"] as List
                this.fields = fieldsList
            }
         }
         return fieldsList
    }

    private List getFieldOptions (List fields){
        def customFieldManager = ComponentAccessor.getCustomFieldManager() 
        def customFields = customFieldManager.getCustomFieldObjects()

        customFields.each { field ->
            def fieldName = field.customFieldType.name

            fields.each { fieldsPair ->
                if (fieldName == fieldsPair["jiraName"]){
                    fieldsPair["new name"] = fieldName
                }
            }
    }
}

}


def qwert = new PortalFields("Payments", "Change Request")
def qaz = qwert.fieldsConfiguration 
log.info(qaz)




// import com.atlassian.jira.component.ComponentAccessor 
// import com.atlassian.jira.issue.fields.screen.*

// import org.apache.log4j.Level 
// import org.apache.log4j.Logger 
// // def log = Logger.getLogger(getClass()) 
// // log.setLevel(Level.DEBUG)

// //change the project key to your own value
// // def project = ComponentAccessor.getProjectManager().getProjectByCurrentKey("RS") 
// // def projectId = project?.id 
// // log.debug "Project: $project.key | $project.name | $projectId"

// //get all the custom fields for the project & all issue types
// def customFieldManager = ComponentAccessor.getCustomFieldManager() 
// def customFields = customFieldManager.getCustomFieldObjects()
// // log.debug "Custom Fields: " + customFields
// customFields.each { field ->
//             def q = field.customFieldType.name

//             log.debug ("id: ${field.id}, name: ${field.name}, type: ${q}")

//  }




// def fieldScreenManager = ComponentAccessor.getFieldScreenManager() 
// def fieldScreens = fieldScreenManager.getFieldScreens() 
// def output = "" 
// log.debug "Screens: " + fieldScreens.name

// fieldScreens.each { fieldScreen -> 

//     def tabs = fieldScreen.getTabs() 
//     output = output + "<h1>" + fieldScreen.name + "</h1>" 

//     tabs.each { FieldScreenTab tab -> 

//         output = output + "<h2>" + tab.name + "</h2>" + "<h3><u>List of Custom Fields:</u></h3>" 
//         def items = tab.getFieldScreenLayoutItems() 

//             items.each { FieldScreenLayoutItem item -> 

//                 if (item.getOrderableField()) { 

//                     if (customFields.contains(item.getOrderableField().name)) { 

//                         output = output + "<p>" + item.getOrderableField().name +  "</p>" 
//                     } 
//                 } 
//             } 
//     } 
// } 

// return output 



// List list = [[1,2],[3,4], 4]

// list.add([5,6])
// list.add("asdc")
// log.info(list)











