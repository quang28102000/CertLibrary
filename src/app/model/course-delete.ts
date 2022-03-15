export interface CourseDelete {
    course_id: string,
    employee_id: string
}

export interface CourseDelete2 {
    course_id: number[],
    employee_id: number[]
}

export interface CourseDeleteDto{
    courseId: number,
    employeeId: number,
    course: string,
    courseLength: number,
    category: string,
    certLink: string,
    email: string,
    endDate: string,
    startDate: string,
    status: string,
    platform: string,
    fullName: string,
    
    checked?: boolean,

}