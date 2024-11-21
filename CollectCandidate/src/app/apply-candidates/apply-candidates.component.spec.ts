import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplyCandidatesComponent } from './apply-candidates.component';

describe('ApplyCandidatesComponent', () => {
  let component: ApplyCandidatesComponent;
  let fixture: ComponentFixture<ApplyCandidatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ApplyCandidatesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ApplyCandidatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
