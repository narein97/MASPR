# MASPR
A simple simulation over anylogic and jade over a Multi Agent System for Power Regulation (MASPR)

- Simulated and built a prototype of a multi-agent system which regulates power generated from wind and energy sources. The simulation was performed over previously generated data.
- Includes JFreeChart and ApachePOI incorporation with Anylogic to extrapolate and analyze the functioning of the agents in the system, building a prototype multi-agent system for the same using JADE.

**NOTE: This code in this repository is quite old and it is very likely to face multiple dependency issues**

#### Usage instruction ####
- **Anylogic Simulation**: Open and run in anylogic
- **JADE prototype**:
  
  Here is a [video](https://www.youtube.com/watch?v=-dFx1_XBGVI) on installing and running jade via eclipse/netbeans

  The libraries for apache poi can be downloaded from [here](https://poi.apache.org/download.html#POI-3.17)
  (Note: The .zip binary distribution needs to be downloaded since windows is the OS)

  To add the external jar files,

  In the IDE
   - For ecllipse
     Go to project properties (right click project-properties)
     click build path -> add external jars
   - For netbeans
     Go to project properties
     click libraries -> add jar/file

  As shown in the video,
  to run all the agents at the same time in the IDE
  run configuraton -> Java Application -> Change Main class to 'jade.Boot' -> In arguments, add
  ' -gui -agents "solar:solar;wind:wind;grid:grid;backup:backup;battery:battery;" '

  (-gui is optional)
