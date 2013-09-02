var formInterface = formInterface || { getModel: function () {
    return window.formModel;
}, getHTML: function () {
    return window.formHTML ? window.formHTML : "Could not find a valid form";
}};
var ziggyFileLoader = ziggyFileLoader || {loadAppData: function (file) {
    if (file === "entity_relationship.json") return "[]";
    return window.formJSON;
}};

var formDataRepositoryContext = formDataRepositoryContext || {
    getFormInstanceByFormTypeAndId: function () {
        return window.formJSON;
    }
}