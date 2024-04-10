# -*- coding: utf-8 -*-

from sqlalchemy import *
from datetime import datetime
import sqlalchemy.types
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm  import  sessionmaker
import pymysql

pymysql.install_as_MySQLdb()
metadata = MetaData()
__engine = create_engine('mysql://root:2222@localhost/blog', connect_args={'charset': 'UTF8'}, echo=True,
                         encoding='UTF-8')

Session_class = sessionmaker(bind=__engine) # 用于session创建

metadata.bind = __engine
Base = declarative_base()

class B_Blog(Base):
    __tablename__ = 'B_BLOG'
    ID = Column('ID', Unicode(255, collation='utf8_bin'), primary_key=True)
    IMAGE_URL = Column('IMAGE_URL', Unicode(255, collation='utf8_bin'), nullable=True)
    DATE = Column('DATE', DateTime, nullable=True)
    TITLE = Column('TITLE', Unicode(255, collation='utf8_bin'), nullable=True)
    TITLE_SECONDARY = Column('TITLE_SECONDARY', Unicode(255, collation='utf8_bin'), nullable=True)
    CONTENT = Column('CONTENT', Text, nullable=True)
    TYPE = Column('TYPE', Integer, default=1)
    EXTERNAL_URL = Column('EXTERNAL_URL', Unicode(255, collation='utf8_bin'))
    CREATOR_ID = Column('CREATOR_ID', Unicode(255, collation='utf8_bin'))
    CREATOR_NAME = Column('CREATOR_NAME', Unicode(255, collation='utf8_bin'))
    CREATE_TIME = Column('CREATE_TIME', DateTime)
    UPDATOR_ID = Column('UPDATOR_ID', Unicode(255, collation='utf8_bin'))
    UPDATOR_NAME = Column('UPDATOR_NAME', Unicode(255, collation='utf8_bin'))
    UPDATE_TIME = Column('UPDATE_TIME', DateTime)
    DELETOR_ID = Column('DELETOR_ID', Unicode(255, collation='utf8_bin'))
    DELETOR_NAME = Column('DELETOR_NAME', Unicode(255, collation='utf8_bin'))
    DELETE_TIME = Column('DELETE_TIME', DateTime)
    DELETE_FLAG = Column('DELETE_FLAG', default=0)


metadata.create_all()