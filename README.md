# Torus Traveller

![image of a torus](torus.png)

* How to run
* Rules
* Considerations
* Implementation comments (see bottom of README)

# How to run
* It's a basic sbt project, so `sbt test` from the root of the cloned
  project should suffice

# Project AC:

## 1. Basic Movement:
- Rover operates on a grid of arbitrary size that wraps North/South and East/West ("asteroids" style)
- Accepts three commands:
  - Move (exclusively forwards)
  - Rotate (clockwise)
  - Rotate (counter-clockwise)

## 2. Autopilot:
- a) Determine shortest path from any given grid coors to any other
- b) Improve solution to include non-traversable terrain

# Considerations:
- Rover
  - immutable, should spawn a new bot rather than mutating itself when performing.
    - (Personally, I like to imagine it builds a new rover, then self-destructs)
- World
  - Represented by a two-dimensional array (or, Seq[Seq[Terrain]])
  - Immutable
  - is **always** square
  - "inverted" Y axis (top is `0`, bottom is `$SIZE - 1`); example grid coords when $SIZE == 4:
```text
(0,0), (1,0), (2,0), (3,0)
(0,1), (1,1), (2,1), (3,1)
(0,2), (1,2), (2,2), (3,2)
(0,3), (1,3), (2,3), (3,3)
```
- Navigation
  - for the MVP (maybe stretching the "V" in that acronym), just navigate
    in an "L" shape (wrapping around if required), then improve/implement more advanced navigator
- Autopilot
  - Should "wrap" the Rover and Navigator to move the rover
  - Should "translate" a path of coordinates into rover-operations (rotating, progressing)

# Implementation comments
I didn't feel like the implementation called for an advanced package
structure, so everything is in a "flat hierarchy"; if we start hooking
this up with a Main function/reading CLI args, I would reconsider. 

```text
codes
    └── mostly
        ├── Compass.scala    // Models North/South/East/West + rotating between them
        ├── Navigation.scala // Models pathfinding (Navigator trait + impl)
        ├── package.scala    // Contains type aliases/minor utilities used throughout
        ├── Rover.scala      // Models the Rover (Rover trait + impl)
        ├── Terrain.scala    // The "contents" of the world, either a mountain: '▲', or an unobstructed tile: '.'
        └── World.scala      // A companion obj for the Seq[Seq[Terrain]] (aliased as World in package.scala) 
```
I ended up making an `apply` method for World - primarily to make
testing easier, so I could parse Worlds from a string rather than creating Seq[Seq[Terrain]]s manually

For instance, a 3x3 world with mountains in NW and SW corners can be instantiated by:

```
val world = """|▲..
               |...
               |▲..""".stripMargin.toWorld // syntactic sugar for World(str: String)
```
, which I figured would make testing the Navigators easier.



### Improvements Missing:
* Thorough failure case testing/verifying error messages
  * Also cleaning up of failure handling (e.g. the "validateSuccessfulParse")
* Thorough testing of Navigator, edge cases aren't covered / implemented
* An obstacle-avoiding Navigator
* An autopilot that actually moves the rover (rover + navigator is currently completely separate)
* I realized while filling this in that it might've been cleaner/beneficial
  to consider the grid a graph rather than staying in the multidimensional "array" mindset

## Dependencies:
- Scalatest