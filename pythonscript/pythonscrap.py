# import libraries
import urllib.request
from bs4 import BeautifulSoup
#import sched, time

#s = sched.scheduler(time.time, time.sleep)

# import only system from os
#from os import system, name



# define our clear function
'''def clear():
    # for windows
    if name == 'nt':
        _ = system('cls')

        # for mac and linux(here, os.name is 'posix')
    else:
        _ = system('clear')
'''

def mycode():

    # specify the url
    quote_page = "https://parking.fullerton.edu/parkinglotcounts/mobile.aspx"

    # query the website and return the html to the variable ‘page’
    page = urllib.request.urlopen(quote_page)

    # parse the html using beautiful soup and store in variable `soup`
    soup = BeautifulSoup(page, 'html.parser')

    # Take out the <div> of name and get its value
    name_box = soup.find('span', attrs={'id': 'gvAvailability_Label_Available_0'})
    name_box1 = soup.find('span', attrs={'id': 'gvAvailability_Label_Available_1'})
    name_box2 = soup.find('span', attrs={'id': 'gvAvailability_Label_Available_2'})
    name_box3 = soup.find('span', attrs={'id': 'gvAvailability_Label_Available_3'})
    name_box4 = soup.find('span', attrs={'id': 'gvAvailability_Label_Available_4'})

    name = name_box.text.strip()
    name1 = name_box1.text.strip()
    name2 = name_box2.text.strip()
    name3 = name_box3.text.strip()
    name4 = name_box4.text.strip()

    print("Parking spot 0: " + name)
    print("Parking spot 1: " + name1)
    print("Parking spot 2: " + name2)
    print("Parking spot 3: " + name3)
    print("Parking spot 4: " + name4)

    # run file after
    #s.enter(6, 1, mycode, (sc,))

mycode()
'''
s.enter(6, 1, mycode, (s,))
s.run()

'''
