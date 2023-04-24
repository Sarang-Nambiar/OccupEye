import firebase_admin
from firebase_admin import credentials, db

#Firebase stuff
cred = credentials.Certificate("key.json")
firebase_admin.initialize_app(cred,{

    'databaseURL':'https://occupeye-dedb8-default-rtdb.asia-southeast1.firebasedatabase.app/'
})

ref = db.reference('/').child('Locations')

def sendData():
    ref.set({
        'Hostel': {
            "Block 55" : {
                "Study Room 4":{
                    "Room Capacity": "10",
                    "Users": 0,        
                    "Colour Grading": "#CBE896"
                },
                "Study Room 2":{
                    "Room Capacity": "10",
                    "Users": 0,        
                    "Colour Grading": "#DB5461"
                },"Test Room":{
                    "Room Capacity": "4",
                    "Users": 0,        
                    "Colour Grading": "#CBE896"
                },
            },
            "Block 57" : {
                "Study Room 4":{
                    "Room Capacity": "10",
                    "Users": 0,        
                    "Colour Grading": "#DB5461"
                },
                "Study Room 2":{
                    "Room Capacity": "10",
                    "Users": 0,        
                    "Colour Grading": "#DB5461"
                },"Study Room 6":{
                    "Room Capacity": "10",
                    "Users": 0,        
                    "Colour Grading": "#DB5461"
                },
            },
            "Block 59" : {
                "Study Room 4":{
                    "Room Capacity": "10",
                    "Users": 0,        
                    "Colour Grading": "#DB5461"
                },
                "Study Room 2":{
                    "Room Capacity": "10",
                    "Users": 0,        
                    "Colour Grading": "#DB5461"
                },"Study Room 9":{
                    "Room Capacity": "10",
                    "Users": 0,        
                    "Colour Grading": "#DB5461"
                },
            }
        }, "College": {
            "Building 2" : {
                "Think Tank 11":{
                    "Room Capacity": "10",
                    "Users": 0,        
                    "Colour Grading": "#DB5461"
                },
                "Think Tank 15":{
                    "Room Capacity": "10",
                    "Users": 0,        
                    "Colour Grading": "#DB5461"
                },"Cohort Class 6":{
                    "Room Capacity": "10",
                    "Users": 0,        
                    "Colour Grading": "#DB5461"
                },
            }
        }, "Library": {
            "Level 2" : {
                "Discussion Room 2":{
                    "Room Capacity": "10",
                    "Users": 0,        
                    "Colour Grading": "#DB5461"
                },
                "Discussion Room 6":{
                    "Room Capacity": "10",
                    "Users": 0,        
                    "Colour Grading": "#CBE896"
                },"Discussion Room 1":{
                    "Room Capacity": "10",
                    "Users": 0,        
                    "Colour Grading": "#DB5461"
                },
            },
            "Level 3" : {
                "Discussion Room 3":{
                    "Room Capacity": "10",
                    "Users": 0,        
                    "Colour Grading": "#DB5461"
                }
            }
        }})

sendData()
