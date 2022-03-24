export interface courseCreate {
  course_id: number,
    tittle: string,
    platform: string,
    category: string,
    image: string,
    courseLength: number,
  
    skills:
      {
        skill_name: string[],
        skill_id: number[]
      },
    skill_flag: number
  }