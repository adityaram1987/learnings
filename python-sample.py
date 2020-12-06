import logging
import time
from logging.handlers import TimedRotatingFileHandler

logger = logging.getLogger('myapp')
#hdlr = logging.FileHandler('./myapp.log')
hdlr = TimedRotatingFileHandler('C:\\Users\\rayadity\\myapp.log', when="m", interval=1, backupCount=5)
formatter = logging.Formatter('%(asctime)s %(levelname)s %(message)s')
hdlr.setFormatter(formatter)
logger.addHandler(hdlr) 
logger.setLevel(logging.WARNING)

logger.error('We have a problem')
logger.info('While this is just chatty')
print ('entered');