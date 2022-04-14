import { TestBed } from '@angular/core/testing';

import { CourseEmployeeService } from './course-employee.service';

describe('CourseEmployeeService', () => {
  let service: CourseEmployeeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CourseEmployeeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
