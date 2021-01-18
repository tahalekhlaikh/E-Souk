
$(function() {
$('button').click(function() { addFields(); });
});

function addFields() {
var html = "<label>Selectionner produits :</label><select th:field='*{produits}'><option th:each='produit : ${produits}' th:value='${produit.designation}' th:text='${produit.designation}'></option></select><label>Quantite</label><input type='text' placeholder='quantite'/><input type='submit' class='btn btn-success' value='Ajouter' id='submit'/>";

 $('#form').append(html);
}