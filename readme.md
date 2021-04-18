# Session Generator

_Note: early development_

Generates Swimming (and eventually running) sessions like:

```
Warmup:
2x (
  1x150m 20sec/rec (fins)
         20sec/rec
)

Mainset:
2x (
  7x100m 20sec/rec (hand pads)
         20sec/rec
  15x50m 20sec/rec (triangle set - 2 easy, 1 hard)
         20sec/rec
)

Cooldown:
1x (
  1x200m 20sec/rec (recovery)
         20sec/rec
)
```

so that you don't need to make them up.

## ToDo:

- [x] create naive sessions for a specific profile input
- [x] Configure the rules for session generation
- [ ] write logic to either email or print the sessions
- [ ] create a UI for the profile input and remove CLI support
- [ ]

## How to run in IntelliJ (alpha)

### 1. Create profile file

JSON file with the below format:

```json
{
  "athletic_level": "beginner",
  "weekly_session_preference": 2,
  "sport_type": "swim"
}
```

- `athletic_level` options: `beginner`, `intermediate`, `advanced`
- `sport_type` options: `swim` (`run` eventually)

### 2. Add Program args:

```
"C:\absolute\path\to\testfile\test.json"
```

### 3. VM Options:

```
-Dlog4j.configuration="log4j.properties"
```

## How it works under the hood

The software will generate sessions similar to the below:

```json
{
  "mainsetSessionStageDetails": {
    "setCount": 2,
    "sessionSets": [
      {
        "setReps": 4,
        "postSessionRestSeconds": 20,
        "setType": "HAND_PADS",
        "setItems": [
          {
            "distance": 300,
            "restSeconds": 20
          }
        ]
      },
      {
        "setReps": 2,
        "postSessionRestSeconds": 20,
        "setType": "FIN_HAND_PADS",
        "setItems": [
          {
            "distance": 150,
            "restSeconds": 20
          }
        ]
      }
    ]
  },
  "warmupSessionStageDetails": {
    "setCount": 1,
    "sessionSets": [
      {
        "setReps": 4,
        "postSessionRestSeconds": 20,
        "setType": "FIN_HAND_PADS",
        "setItems": [
          {
            "distance": 50,
            "restSeconds": 20
          }
        ]
      },
      {
        "setReps": 3,
        "postSessionRestSeconds": 20,
        "setType": "AEROBIC",
        "setItems": [
          {
            "distance": 100,
            "restSeconds": 20
          }
        ]
      }
    ]
  },
  "cooldownSessionStageDetails": {
    "setCount": 1,
    "sessionSets": [
      {
        "setReps": 6,
        "postSessionRestSeconds": 20,
        "setType": "RECOVERY",
        "setItems": [
          {
            "distance": 50,
            "restSeconds": 20
          }
        ]
      },
      {
        "setReps": 1,
        "postSessionRestSeconds": 20,
        "setType": "RECOVERY",
        "setItems": [
          {
            "distance": 100,
            "restSeconds": 20
          }
        ]
      }
    ]
  }
}
```

The above will translate to a session output of:

```
Warmup:
1x (
  4x50m 20sec/rec (fins & hand pads)
        20sec/rec
  3x100m 20sec/rec (aerobic)
         20sec/rec
)
Mainset:
2x (
  4x300m 20sec/rec (hand pads)
         20sec/rec
  2x150m 20sec/rec (fins & hand pads)
         20sec/rec
)
Cooldown:
1x (
  6x50m 20sec/rec (recovery)
        20sec/rec
  1x100m 20sec/rec (recovery)
         20sec/rec
)
```
