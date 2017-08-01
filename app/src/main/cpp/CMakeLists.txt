cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Wall")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -Wall")

add_library(plasma SHARED
            plasma.c)
            

# Include libraries needed for plasma lib
target_link_libraries(plasma
                      android
                      jnigraphics
                      log
                      m)

add_library(accelerometergraph SHARED
            sensorgraph.cpp)
            
            
# Include libraries needed for accelerometergraph lib
target_link_libraries(accelerometergraph
                      android
                      GLESv2
                      log)
                      
                      
add_library(myc SHARED
            myc.c)
            
# Include libraries needed for myc lib
target_link_libraries(myc
                      android
                      log
                      m)
