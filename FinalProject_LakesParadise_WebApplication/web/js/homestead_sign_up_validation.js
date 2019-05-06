$(document).ready(function() {
    $('#homestead_reg_form').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: 'The name is required and cannot be empty'
                    },
                    stringLength: {
                        min: 2,
                        max: 45,
                        message: 'Please enter at least 2 characters and no more than 45'
                    }
                }
            },
            description: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and cannot be empty'
                    },
                    stringLength: {
                        min: 5,
                        max: 45,
                        message: 'Please enter at least 5 characters and no more than 45'
                    }
                }
            },
            price: {
                validators: {
                    notEmpty: {
                        message: 'The name is required and cannot be empty'
                    },
                    stringLength: {
                        min: 2,
                        max: 10,
                        message: 'Please enter at least 2 characters and no more than 10'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z]+$/,
                        message: 'The username can only consist of Latins letters'
                    }
                }
            },
            peopleNumber: {
                validators: {
                    notEmpty: {
                        message: 'The surname is required and cannot be empty'
                    },
                    stringLength: {
                        min: 2,
                        max: 30,
                        message: 'Please enter at least 2 characters and no more than 30'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z]+$/,
                        message: 'The surname can only consist of Latins letters'
                    }
                }
            },
            phoneNumber: {
                validators: {
                    numeric: {
                        message: 'The phone number must have only numbers'
                    },
                    notEmpty: {
                        message: 'The content is required and cannot be empty'
                    },
                    stringLength: {
                        min: 7,
                        max: 12,
                        message: 'Please enter at least 7 characters and no more than 12'
                    }
                }
            }
        }
    });
});