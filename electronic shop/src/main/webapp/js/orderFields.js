var selectElem = document.getElementById('delivery')
var paymentElem = document.getElementById('payment')
var city = document.getElementById('city')
var cityLabel = document.getElementById('cityLabel')
var department = document.getElementById('department')
var departmentLabel = document.getElementById('departmentLabel')
var street = document.getElementById('street')
var streetLabel = document.getElementById('streetLabel')
var apartment = document.getElementById('apartment')
var apartmentLabel = document.getElementById('apartmentLabel')
var firstName = document.getElementById('firstName')
var firstNameLabel = document.getElementById('firstNameLabel')
var surname = document.getElementById('surname')
var surnameLabel = document.getElementById('surnameLabel')
var phone = document.getElementById('phone')
var phoneLabel = document.getElementById('phoneLabel')
var card = document.getElementById('card')
var cardLabel = document.getElementById('cardLabel')
var cvv2 = document.getElementById('cvv2')
var cvv2Label = document.getElementById('cvv2Label')
var data = document.getElementById('data')
var dataLabel = document.getElementById('dataLabel')
var errorCity = document.getElementById('errorCity')


// Когда выбран новый элемент <option>
selectElem.addEventListener('change', function() {
    var index = selectElem.value;
    if (index == 1){
        city.style.display = 'block';
        cityLabel.style.display = 'block';
        department.style.display = 'block';
        departmentLabel.style.display = 'block';
        showUser();
        street.style.display = 'none';
        streetLabel.style.display = 'none';
        apartment.style.display = 'none';
        apartmentLabel.style.display = 'none';
        errorCity.style.display = 'block';
    }
    if(index == 2){
        city.style.display = 'block';
        cityLabel.style.display = 'block';
        department.style.display = 'none';
        departmentLabel.style.display = 'none';
        showUser();
        street.style.display = 'block';
        streetLabel.style.display = 'block';
        apartment.style.display = 'block';
        apartmentLabel.style.display = 'block';
        errorCity.style.display = 'block';
    }
    if(index == 3){
        city.style.display = 'none';
        cityLabel.style.display = 'none';
        department.style.display = 'none';
        departmentLabel.style.display = 'none';
        street.style.display = 'none';
        streetLabel.style.display = 'none';
        apartment.style.display = 'none';
        apartmentLabel.style.display = 'none';
        errorCity.style.display = 'none';
        showUser();
    }
})
function showUser(){
    firstName.style.display = 'block';
    firstNameLabel.style.display = 'block';
    surname.style.display = 'block';
    surnameLabel.style.display = 'block';
    phone.style.display = 'block';
    phoneLabel.style.display = 'block';
}
paymentElem.addEventListener('change', function() {
    var index = paymentElem.value;
    if (index == 1){
        card.style.display = 'none';
        cardLabel.style.display = 'none';
        data.style.display = 'none';
        dataLabel.style.display = 'none';
        cvv2.style.display = 'none';
        cvv2Label.style.display = 'none';
    }
    if(index == 2){
        card.style.display = 'block';
        cardLabel.style.display = 'block';
        data.style.display = 'block';
        dataLabel.style.display = 'block';
        cvv2.style.display = 'block';
        cvv2Label.style.display = 'block';
        }
})
function hiddenAllEmptyFields(){
    empty(city,cityLabel);
    empty(department,departmentLabel);
    empty(street,streetLabel);
    empty(apartment,apartmentLabel);
    empty(firstName,firstNameLabel);
    empty(surname,surnameLabel);
    empty(phone,phoneLabel);
    empty(card,cardLabel);
    empty(data,dataLabel);
    empty(cvv2,cvv2Label);
    var index = selectElem.value;
    if (index == 1){
        city.style.display = 'block';
        cityLabel.style.display = 'block';
        department.style.display = 'block';
        departmentLabel.style.display = 'block';
        showUser();
        street.style.display = 'none';
        streetLabel.style.display = 'none';
        apartment.style.display = 'none';
        apartmentLabel.style.display = 'none';
    }
    if(index == 2){
        city.style.display = 'block';
        cityLabel.style.display = 'block';
        department.style.display = 'none';
        departmentLabel.style.display = 'none';
        showUser();
        street.style.display = 'block';
        streetLabel.style.display = 'block';
        apartment.style.display = 'block';
        apartmentLabel.style.display = 'block';
    }
    if(index == 3){
        city.style.display = 'none';
        cityLabel.style.display = 'none';
        department.style.display = 'none';
        departmentLabel.style.display = 'none';
        street.style.display = 'none';
        streetLabel.style.display = 'none';
        apartment.style.display = 'none';
        apartmentLabel.style.display = 'none';
        showUser();
    }
    var index1 = paymentElem.value;
    if (index1 == 1){
        card.style.display = 'none';
        cardLabel.style.display = 'none';
        data.style.display = 'none';
        dataLabel.style.display = 'none';
        cvv2.style.display = 'none';
        cvv2Label.style.display = 'none';
    }
    if(index1 == 2){
        card.style.display = 'block';
        cardLabel.style.display = 'block';
        data.style.display = 'block';
        dataLabel.style.display = 'block';
        cvv2.style.display = 'block';
        cvv2Label.style.display = 'block';
    }

}
function empty(element,elementLabel){
    if (element.value == ""){
        element.style.display = 'none';
        elementLabel.style.display = 'none';
    }
}